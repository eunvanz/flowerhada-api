package com.flowerhada.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.flowerhada.config.ServerConfig;
import com.flowerhada.domain.MainBanner;
import com.flowerhada.service.MainBannerService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("main-banners")
public class MainBannerController {
	
	@Autowired MainBannerService mainBannerService;
	
	@PostMapping()
	public MainBanner createMainBanner(@ModelAttribute MainBanner mainBanner) {
//		MultipartFile sourceFile = mainBanner.getImgFile();
//		String sourceFileName = sourceFile.getOriginalFilename();
//		System.out.println("fileName: " + sourceFileName);
//		String sourceFileNameExtension = FilenameUtils.getExtension(sourceFileName).toLowerCase();
//		
//		String destinationFileName;
//		File destinationFile;
//		
//		destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
//		destinationFile = new File(ServerConfig.MAIN_BANNER_IMAGE_URL + destinationFileName);
//		System.out.println("file created: " + destinationFile.toString());
//		
//		destinationFile.getParentFile().mkdirs();
//		System.out.println("directory created");
//		try {
//			sourceFile.transferTo(destinationFile);
//		} catch (IllegalStateException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("file transfered");
//		
//		mainBanner.setImg(destinationFileName);
//		
//		System.out.println("mainBanner: " + mainBanner);
		
		return mainBannerService.createMainBanner(mainBanner);
	}
	
	@GetMapping()
	public List<MainBanner> readMainBanners() {
		return mainBannerService.readMainBanners();
	}
	
	@GetMapping("/{id}")
	public MainBanner readMainBanner(@PathVariable Long id) {
		return mainBannerService.readMainBanner(id);
	}
	
	@PutMapping("/{id}")
	public MainBanner updateMainBanner(@ModelAttribute MainBanner mainBanner, @PathVariable Long id) {
		mainBanner.setId(id);
		return mainBannerService.updateMainBanner(mainBanner);
	}
	
	@DeleteMapping("/{id}")
	public void deleteMainBanner(@PathVariable Long id) {
		mainBannerService.deleteMainBanner(id);
	}

}
