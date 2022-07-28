package com.atguigu.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.atguigu.base.BaseController;
import com.atguigu.entity.HouseImage;
import com.atguigu.result.Result;
import com.atguigu.service.HouseImageService;
import com.atguigu.util.QiniuUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * @Author mabo
 * @Date 2022/7/28 12:39
 */
@Controller
@RequestMapping("/houseImage")
public class HouseImageController extends BaseController {
    @Reference
    private HouseImageService houseImageService;

    private final static String PAGE_UPLOAD_SHOW = "house/upload";
    private final static String LIST_ACTION = "redirect:/house";

    ///houseImage/uploadShow/[[${house.id}]]/1
    @RequestMapping("/uploadShow/{houseId}/{type}")
    public String uploadShow(Model model, @PathVariable Long houseId, @PathVariable Long type) {
        model.addAttribute("houseId", houseId);
        model.addAttribute("type", type);
        return PAGE_UPLOAD_SHOW;
    }

    ///houseImage/upload/[[${houseId}]]/[[${type}]]
    @RequestMapping("/upload/{houseId}/{type}")
    @ResponseBody//添加完之后停留在该页面，不需要跳转
    public Result upload(@PathVariable Long houseId, @PathVariable Integer type, @RequestParam("file") MultipartFile[] files) throws IOException {
        if (files.length > 0) {
            for (MultipartFile file : files) {
                String originalFilename = file.getOriginalFilename();
                String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFileName = UUID.randomUUID().toString() + extName;
                QiniuUtil.upload2Qiniu(file.getBytes(), newFileName);

                String url = "http://rflxvayze.hb-bkt.clouddn.com/" + newFileName;

                HouseImage houseImage = new HouseImage();
                houseImage.setHouseId(houseId);
                houseImage.setType(type);
                houseImage.setImageName(file.getOriginalFilename());
                houseImage.setImageUrl(url);
                houseImageService.insert(houseImage);
            }
        }
        return Result.ok();
    }

    ///delete/[[${house.id}]]/'+id
    @RequestMapping("/delete/{houseId}/{id}")
    public String delete(@PathVariable Long houseId, @PathVariable Long id) {
        HouseImage houseImage = houseImageService.getById(id);
        houseImageService.delete(id);
        String imageUrl = houseImage.getImageUrl();
        String houseImageUrl = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        QiniuUtil.deleteFileFromQiniu(houseImageUrl);
        return LIST_ACTION + "/" + houseId;
    }
}
