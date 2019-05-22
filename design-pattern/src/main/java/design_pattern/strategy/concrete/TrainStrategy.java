package design_pattern.strategy.concrete;

import design_pattern.strategy.abs.TravelStrategy;

/***
 * 具体策略类(ConcreteStrategy)
 * @author think
 *
 */
public class TrainStrategy implements TravelStrategy {

	public void travelWay() {
		System.out.println("旅游方式选择：乘坐火车");
	}

}
