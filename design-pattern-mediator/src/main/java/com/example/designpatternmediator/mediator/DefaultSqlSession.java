package com.example.designpatternmediator.mediator;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.security.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DefaultSqlSession implements SqlSession {

    private Connection connection;

    private Map<String, XNode> mapperElement;

    public DefaultSqlSession(Connection connection, Map<String, XNode> mapperElement) {
        this.connection = connection;
        this.mapperElement = mapperElement;
    }

    @Override
    public <T> T selectOne(String statement) {
        XNode xNode = mapperElement.get(statement);
        try (PreparedStatement prepareStatement = connection.prepareStatement(xNode.getSql());) {
            ResultSet resultSet = prepareStatement.executeQuery();
            List<T> objects = resultSet2Obj(resultSet, Class.forName(xNode.getResultType()));
            return objects.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        XNode xNode = mapperElement.get(statement);
        Map<Integer, String> parameterMap = xNode.getParameter();
        try (PreparedStatement preparedStatement = connection.prepareStatement(xNode.getSql());) {
            buildParameter(preparedStatement, parameter, parameterMap);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<T> objects = resultSet2Obj(resultSet, Class.forName(xNode.getResultType()));
            return objects.get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public <T> List<T> selectList(String statement) {
        XNode xNode = mapperElement.get(statement);
        try (PreparedStatement prepareStatement = connection.prepareStatement(xNode.getSql());) {
            ResultSet resultSet = prepareStatement.executeQuery();
            return resultSet2Obj(resultSet, Class.forName(xNode.getResultType()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    
    @Override
    public <T> List<T> selectList(String statement, Object parameter) {
        XNode xNode = mapperElement.get(statement);
        Map<Integer, String> parameterMap = xNode.getParameter();
        try (PreparedStatement preparedStatement = connection.prepareStatement(xNode.getSql());) {
            buildParameter(preparedStatement, parameter, parameterMap);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet2Obj(resultSet, Class.forName(xNode.getResultType()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public void close() {
        if (null == connection) return;
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T> List<T> resultSet2Obj(ResultSet resultSet, Class<?> clazz) {
        List<T> list = new ArrayList<>();
        try {
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 每次遍历行值
            while (resultSet.next()) {
                T obj = (T) clazz.getDeclaredConstructor().newInstance();
                for (int i = 1; i < columnCount; i++) {
                    Object value = resultSet.getObject(i);
                    String columnName = metaData.getColumnName(i);
                    String setMethod = "set" + columnName.substring(0, 1).toUpperCase() + columnName.substring(1);
                    Method method;
                    if (value instanceof Timestamp) {
                        method = clazz.getMethod(setMethod, Date.class);
                    } else {
                        method = clazz.getMethod(setMethod, value.getClass());
                    }
                    method.invoke(obj, value);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    private void buildParameter(PreparedStatement preparedStatement, Object parameter,
            Map<Integer, String> parameterMap) throws Exception {

        int size = parameterMap.size();

        // 单个参数
        if (parameter instanceof Long) {
            for (int i = 1; i <= size; i++) {
                preparedStatement.setLong(i, Long.parseLong(parameter.toString()));
            }
            return;
        }
        if (parameter instanceof Integer) {
            for (int i = 1; i <= size; i++) {
                preparedStatement.setInt(i, Integer.parseInt(parameter.toString()));
            }
            return;
        }
        if (parameter instanceof String) {
            for (int i = 1; i < size; i++) {
                preparedStatement.setString(i, parameter.toString());
            }
            return;
        }


        // 对象参数
        Map<String, Object> filedMap = new HashMap<>();
        Field[] declaredFields = parameter.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            String name = field.getName();
            field.setAccessible(true);
            Object obj = field.get(parameter);
            field.setAccessible(false);
            filedMap.put(name, obj);
        }

        for (int i = 1; i <= size; i++) {
            String parameterDefine = parameterMap.get(i);
            Object obj = filedMap.get(parameterDefine);
            if (obj instanceof Short) {
                preparedStatement.setShort(i, Short.parseShort(obj.toString()));
                continue;
            }
            if (obj instanceof Integer) {
                preparedStatement.setInt(i, Integer.parseInt(obj.toString()));
                continue;
            }
            if (obj instanceof Long) {
                preparedStatement.setLong(i, Long.parseLong(obj.toString()));
                continue;
            }
            if (obj instanceof String) {    
                preparedStatement.setString(i, obj.toString());
                continue;
            }
            if (obj instanceof Date) {
                preparedStatement.setDate(i, (java.sql.Date) obj);
            }
        }
    }


}
