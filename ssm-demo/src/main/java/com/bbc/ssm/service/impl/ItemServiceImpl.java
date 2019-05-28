package com.bbc.ssm.service.impl;

import com.bbc.ssm.mapper.ItemMapper;
import com.bbc.ssm.po.Item;
import com.bbc.ssm.po.ItemExample;
import com.bbc.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author fitbbc
 * @date 2019/05/27
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;
    @Override
    public List<Item> queryItem() {
        return itemMapper.selectByExample(new ItemExample());
    }

    @Override
    public Item queryItemById(Integer id) {
        return itemMapper.selectByPrimaryKey(id);
    }
}
