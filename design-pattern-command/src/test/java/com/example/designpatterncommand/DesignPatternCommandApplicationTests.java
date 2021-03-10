package com.example.designpatterncommand;

import com.example.designpatterncommand.cook.impl.GuangDongCook;
import com.example.designpatterncommand.cook.impl.JiangSuCook;
import com.example.designpatterncommand.cook.impl.ShangDongCook;
import com.example.designpatterncommand.cook.impl.SiChuanCook;
import com.example.designpatterncommand.cuisine.ICuisine;
import com.example.designpatterncommand.cuisine.impl.GuangDongCuisine;
import com.example.designpatterncommand.cuisine.impl.JiangSuCuisine;
import com.example.designpatterncommand.cuisine.impl.ShangDongCuisine;
import com.example.designpatterncommand.cuisine.impl.SiChuanCuisine;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DesignPatternCommandApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	// 菜系 + 厨师；广东（粤菜）、江苏（苏菜）、⼭山东（鲁菜）、四川（川菜）
	public void test() {
		ICuisine guangDoneCuisine = new GuangDongCuisine(new GuangDongCook());
		JiangSuCuisine jiangSuCuisine = new JiangSuCuisine(new JiangSuCook());
		ShangDongCuisine shanDongCuisine = new ShangDongCuisine(new ShangDongCook());
		SiChuanCuisine siChuanCuisine = new SiChuanCuisine(new SiChuanCook());

		// 点餐
		XiaoEr xiaoEr = new XiaoEr();
		xiaoEr.order(guangDoneCuisine);
		xiaoEr.order(jiangSuCuisine);
		xiaoEr.order(shanDongCuisine);
		xiaoEr.order(siChuanCuisine);

		// 下单
		xiaoEr.placeOrder();

	}

}
