package design_pattern.strategy;

import design_pattern.strategy.abs.TravelStrategy;
import design_pattern.strategy.concrete.AirPlanelStrategy;
import design_pattern.strategy.concrete.BicycleStrategy;
import design_pattern.strategy.concrete.TrainStrategy;

/**
 * 测试类
 * @author think
 *
 */
public class StrategyTest {

	public static void main(String[] args) {

		//抽象策略类
		TravelStrategy strategy = null;
		PersonContext person = null;
		
		// 太远了，需要做飞机
		strategy = new AirPlanelStrategy() ;
		person = new PersonContext(strategy);
		person.travel();
		
		// 不太远，飞机太贵，选择火车
		strategy = new TrainStrategy();
		person.setStrategy(strategy);
		person.travel();
		
		// 很近，直接选择自行车
		strategy = new BicycleStrategy();
		person.setStrategy(strategy);
		person.travel();
	}
}
