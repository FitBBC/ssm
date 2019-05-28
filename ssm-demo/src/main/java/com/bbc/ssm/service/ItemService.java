package com.bbc.ssm.service;

import com.bbc.ssm.po.Item;

import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/27
 */
public interface ItemService {

    public List<Item> queryItem();

    public Item queryItemById(Integer id);
}
