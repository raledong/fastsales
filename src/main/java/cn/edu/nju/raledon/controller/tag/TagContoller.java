package cn.edu.nju.raledon.controller.tag;

import cn.edu.nju.raledon.service.tag.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by rale on 7/12/17.
 * Tag标签获取或添加
 */
@Controller
@RequestMapping(value = "/tag")
public class TagContoller {

    @Autowired
    private TagService tagService;

    /**
     * 根据关键词获得标签词条
     * @param keyword
     * @return List<String>标签数组
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public @ResponseBody
    List<String> search(String keyword){
        System.out.println("keyword"+keyword);
        if (keyword != null || !keyword.equals("")){
            return tagService.search(keyword);
        }
        return null;
    }
}
