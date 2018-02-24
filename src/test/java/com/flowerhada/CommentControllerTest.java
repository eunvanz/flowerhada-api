package com.flowerhada;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.flowerhada.controller.CommentController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentControllerTest {

	Logger logger = Logger.getLogger(this.getClass());
	
	private MockMvc mockMvc;
	
	@Autowired
	private CommentController commentController;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = standaloneSetup(commentController).build();
	}
	
	@Test
	public void contextLoads() {
	}
	
//	@Test
//	public void testPostMainBanner() throws Exception {
//		MvcResult result = mockMvc.perform(
//                post("/comments")
//                .param("title", "unittest title")
//                .param("content", "unittest content")
//                .param("img", "asdfasfkl;jasdfl;kas")
//                .param("userId", "1")
//                .param("type", "review"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andReturn();
//		
//		logger.info(result.getResponse().getContentAsString());
//
//	}
	
}
