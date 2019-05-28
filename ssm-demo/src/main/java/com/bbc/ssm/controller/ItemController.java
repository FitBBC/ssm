package com.bbc.ssm.controller;

import com.bbc.ssm.exception.BusinessException;
import com.bbc.ssm.po.Item;
import com.bbc.ssm.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.UUID;

/**
 * @author fitbbc
 * @date 2019/05/27
 */
@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    public List<Item> queryItem() {
        return itemService.queryItem();
    }
    @RequestMapping(value="removeItem", params= {"name","price>5000"})
    public String removeItem(Model model) {
        model.addAttribute("msg", "ItemController...removeItem方法执行了");
        return "success";
    }

    @RequestMapping("{id}/{name}")
    @ResponseBody
    public Item queryItemById(@PathVariable Integer id, @PathVariable String name){
        return null;
    }

    @RequestMapping(value = "/showItemEdit")
    @ResponseBody
    public Item showItemEdit(Integer id) throws Exception{
        // 查询要显示的商品内容
        Item item = itemService.queryItemById(id);
        if(item == null) throw new BusinessException("查询不到商品无法修改");
        return item;
    }

    @RequestMapping("fileupload")
    public String findUserById(MultipartFile uploadFile) throws Exception {
        // 编写文件上传逻辑(mvc模式和三层结构模式)
        // 三层模式:表现层(controller、action)、业务层(service、biz)、持久层(dao、 mapper)
        // MVC模式主要就是来解决表现层的问题的(原始的表现层是使用Servlet编写，即编写业务逻辑，又 编写视图展示)
        if (uploadFile != null) {
            System.out.println(uploadFile.getOriginalFilename());
            // 原始图片名称
            String originalFilename = uploadFile.getOriginalFilename();
            // 如果没有图片名称，则上传不成功
            if (originalFilename != null && originalFilename.length() > 0) {
                // 存放图片的物理路径
                String picPath = "E:\\";
                // 获取上传文件的扩展名
                String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
                // 新文件的名称
                String newFileName = UUID.randomUUID() + extName;
                // 新的文件
                File newFile = new File(picPath + newFileName);
                // 把上传的文件保存成一个新的文件
                uploadFile.transferTo(newFile);
                // 同时需要把新的文件名更新到数据库中 }
            }
        }
        return "文件上传成功";
    }
}
