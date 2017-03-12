/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.eqcode.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.UUID;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.ImageOverlayUtils;
import com.thinkgem.jeesite.common.utils.ZxingHandler;
import com.thinkgem.jeesite.modules.eqcode.entity.PeakEqcodeRepeat;
import com.thinkgem.jeesite.modules.eqcode.util.EqcodeData;
import com.thinkgem.jeesite.modules.eqcode.util.ReadExcel;
import com.thinkgem.jeesite.modules.eqcode.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.eqcode.entity.PeakEqcode;
import com.thinkgem.jeesite.modules.eqcode.dao.PeakEqcodeDao;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * 生成二维码错误Service
 * @author ccs
 * @version 2017-03-05
 */
@Service
//@Transactional(readOnly = true)
public class PeakEqcodeService extends CrudService<PeakEqcodeDao, PeakEqcode> {

	@Autowired
	private PeakEqcodeRepeatService peakEqcodeRepeatService;

	public PeakEqcode get(String id) {
		return super.get(id);
	}

	@Value("${bgEqPic}")
	private String 	qrCodeBgFilePath;

	@Value("${accessUrl:http://kh56.61why.com/peakstatic}")
	private String accessUrl;

	@Transactional(readOnly = false)
	public List<PeakEqcode> findList(PeakEqcode peakEqcode) {
		return super.findList(peakEqcode);
	}
	
	public Page<PeakEqcode> findPage(Page<PeakEqcode> page, PeakEqcode peakEqcode) {
		return super.findPage(page, peakEqcode);
	}
	
	//@Transactional(readOnly = false)
	public void save(PeakEqcode peakEqcode) {
		super.save(peakEqcode);
	}
	
	@Transactional(readOnly = false)
	public void delete(PeakEqcode peakEqcode) {
		super.delete(peakEqcode);
	}


	public void dealFile(CommonsMultipartFile file){
		//File f = new File("~/"+now());
		String prefix = Util.getPostfix(file.getOriginalFilename());
		logger.info("上传文件文件名："+file.getOriginalFilename());
		try {
			List<EqcodeData> list = ReadExcel.readExcel(file.getInputStream(),prefix);
			for(EqcodeData ed : list){
				System.out.println(ed.getEqcodeText());
				PeakEqcode pe = new PeakEqcode();
				pe.setEqcodeText(ed.getEqcodeText());
				List<PeakEqcode> list1 = findList(pe);
				if(list1.size()>0){
					PeakEqcodeRepeat per = new PeakEqcodeRepeat();
					//per.setId(UUID.randomUUID().toString().replace("-",""));
					per.setCreateTime(new Date());
					per.setEqcodeText(ed.getEqcodeText());
					peakEqcodeRepeatService.save(per);
				}else {
					//pe.setId(UUID.randomUUID().toString().replace("-",""));
					pe.setCreateTime(new Date());
					String img = createQRCodeImg(ed.getEqcodeText());
					pe.setImgPath(accessUrl+img);
					save(pe);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String now(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		return sdf.format(new Date());
	}

	/**
	 * 根据参数生成包含边框的二维码图片，返回图片的相对路径
	 * @param content
	 * @return
	 */
	public String createQRCodeImg(String content) {
		String qrCodeImgpath = Global.getUserfilesBaseDir() + Global.USERFILES_BASE_URL + "tmp/" + IdGen.uuid() + ".png";
		//叠加后图片的相对路径
		String overlayedRelativeImgpath = Global.USERFILES_BASE_URL + "qrcode/" + IdGen.uuid() + ".png";
		//叠加后图片的绝对路径
		String overlayedAbsolutImgpath = Global.getUserfilesBaseDir() + overlayedRelativeImgpath;
		//String qrCodeBgFilePath = Global.getProjectPath() + "/static/images/qrcode_bg.jpg";
		ZxingHandler.encode2(content, 265, 265, qrCodeImgpath);


		// 构建叠加层
		BufferedImage buffImg = null;
		try {
			//使用window 测试
			buffImg = ImageOverlayUtils.watermark(new File(qrCodeBgFilePath), new File(qrCodeImgpath), 146, 233, 1.0f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 保持叠加后的图片
		ImageOverlayUtils.generateWaterFile(buffImg, overlayedAbsolutImgpath);
		return overlayedRelativeImgpath;
	}

//	public static void main(String[] args) {
//		PeakEqcodeService service = new PeakEqcodeService();
//		System.setProperty("userfiles.basedir","G:/temp");
//		service.qrCodeBgFilePath = "D:\\workspace\\peak\\src\\main\\webapp\\static\\images\\qrcode_bg.jpg";
//		String str = service.createQRCodeImg("tessxx");
//		System.out.println(str);
//	}
}