package com.flowerhada.service;

import java.util.List;

import com.flowerhada.domain.MainBanner;

public interface MainBannerService {
	public MainBanner createMainBanner(MainBanner mainBanner);
	public void deleteMainBanner(Long id);
	public MainBanner updateMainBanner(MainBanner mainBanner);
	public MainBanner readMainBanner(Long id);
	public List<MainBanner> readMainBanners();
}
