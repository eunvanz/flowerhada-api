package com.flowerhada;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.flowerhada.controller.MainBannerController;

import static org.hamcrest.CoreMatchers.equalTo;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MainBannerControllerTest {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	private MockMvc mockMvc;
	
	@Autowired
	private MainBannerController mainBannerController;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = standaloneSetup(mainBannerController).build();
	}
	
	@Test
	public void contextLoads() {
	}
	
//	@Test
//	public void testPostMainBanner() throws Exception {
//		MvcResult result = mockMvc.perform(
//                post("/main-banners")
//                .param("title", "unittest title")
//                .param("shortTitle", "short title")
//                .param("detail", "unittest content")
//                .param("activated", "true")
//                .param("img", "asdfasfkl;jasdfl;kas")
//                .param("link", "/link"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andReturn();
//		
//		logger.info(result.getResponse().getContentAsString());
//
//	}
	
//	@Test
//	public void testPutMainBanenr() throws Exception {
//		MvcResult result = mockMvc.perform(
//              put("/main-banners/20")
//              .param("title", "unittest title")
//              .param("shortTitle", "short title")
//              .param("detail", "unittest content")
//              .param("activated", "true")
//              .param("img", "asdfasfkl;jasdfl;kas")
//              .param("link", "/link"))
//              .andExpect(status().isOk())
//              .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//              .andReturn();
//		
//		logger.info(result.getResponse().getContentAsString());
//	}

}
