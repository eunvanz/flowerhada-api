package com.flowerhada.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.flowerhada.domain.MainBanner;
import com.flowerhada.repository.MainBannerRepository;
import com.flowerhada.service.MainBannerService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MainBannerServiceImpl implements MainBannerService {
	
	@Autowired MainBannerRepository mainBannerRepository;

	@Override
	public MainBanner createMainBanner(MainBanner mainBanner) {
		return mainBannerRepository.save(mainBanner);
	}

	@Override
	public void deleteMainBanner(Long id) {
		mainBannerRepository.delete(id);
	}

	@Override
	public MainBanner updateMainBanner(MainBanner mainBanner) {
		MainBanner oldMainBanner = mainBannerRepository.findOne(mainBanner.getId());
		oldMainBanner.setActivated(mainBanner.isActivated());
		oldMainBanner.setDetail(mainBanner.getDetail());
		oldMainBanner.setImg(mainBanner.getImg());
		oldMainBanner.setLink(mainBanner.getLink());
		oldMainBanner.setTitle(mainBanner.getTitle());
		oldMainBanner.setShortTitle(mainBanner.getShortTitle());
		return oldMainBanner;
	}

	@Override
	public MainBanner readMainBanner(Long id) {
		return mainBannerRepository.findOne(id);
	}

	@Override
	public List<MainBanner> readMainBanners() {
		return mainBannerRepository.findAll();
	}

}
