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
import com.flowerhada.controller.UserController;

import static org.hamcrest.CoreMatchers.equalTo;  
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;  
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status; 
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {
	
	Logger logger = Logger.getLogger(this.getClass());
	
	private MockMvc mockMvc;
	
	@Autowired
	private UserController userController;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = standaloneSetup(userController).build();
	}
	
	@Test
	public void contextLoads() {
	}
	
	@Test
	public void testFindById() throws Exception {
		
		MvcResult result = mockMvc.perform(
                get("/users/2"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andReturn();
		
		logger.info(result.getResponse().getContentAsString());

	}

}
