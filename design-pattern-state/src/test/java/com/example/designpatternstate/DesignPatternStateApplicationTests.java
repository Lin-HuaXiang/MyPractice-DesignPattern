package com.example.designpatternstate;

import com.alibaba.fastjson.JSON;
import com.example.designpatternstate.matter.ActivityService;
import com.example.designpatternstate.matter.Status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class DesignPatternStateApplicationTests {

	@Test
	public void test_Editing2Arraignment() {
		String activityId = "100001";
		ActivityService.init(activityId, Status.Editing);
		StateHandler stateHandler = new StateHandler();
		Result result = stateHandler.arraignment(activityId, Status.Editing);
		log.info("测试结果(编辑中To提审活动)：{}", JSON.toJSONString(result));
		log.info("活动信息：{} 状态：{}", JSON.toJSONString(ActivityService.queryActivityInfo(activityId)),
				JSON.toJSONString(ActivityService.queryActivityInfo(activityId).getStatus()));
	}

	@Test
	public void test_Editing2Open() {
		String activityId = "100001";
		ActivityService.init(activityId, Status.Editing);
		StateHandler stateHandler = new StateHandler();
		Result result = stateHandler.open(activityId, Status.Editing);
		log.info("测试结果(编辑中To开启活动)：{}", JSON.toJSONString(result));
		log.info("活动信息：{} 状态：{}", JSON.toJSONString(ActivityService.queryActivityInfo(activityId)),
				JSON.toJSONString(ActivityService.queryActivityInfo(activityId).getStatus()));
	}

	@Test
	public void test_Refuse2Doing() {
		String activityId = "100001";
		ActivityService.init(activityId, Status.Refuse);
		StateHandler stateHandler = new StateHandler();
		Result result = stateHandler.doing(activityId, Status.Refuse);
		log.info("测试结果(拒绝To活动中)：{}", JSON.toJSONString(result));
		log.info("活动信息：{} 状态：{}", JSON.toJSONString(ActivityService.queryActivityInfo(activityId)),
				JSON.toJSONString(ActivityService.queryActivityInfo(activityId).getStatus()));
	}

	@Test
	public void test_Refuse2Revoke() {
		String activityId = "100001";
		ActivityService.init(activityId, Status.Refuse);
		StateHandler stateHandler = new StateHandler();
		Result result = stateHandler.checkRevoke(activityId, Status.Refuse);
		log.info("测试结果(拒绝To撤审)：{}", JSON.toJSONString(result));
		log.info("活动信息：{} 状态：{}", JSON.toJSONString(ActivityService.queryActivityInfo(activityId)),
				JSON.toJSONString(ActivityService.queryActivityInfo(activityId).getStatus()));
	}

}
