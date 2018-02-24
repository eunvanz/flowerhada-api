package com.flowerhada;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.sql.Time;
import java.text.SimpleDateFormat;

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

import com.flowerhada.controller.LessonDayController;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LessonDayControllerTest {
Logger logger = Logger.getLogger(this.getClass());
	
	private MockMvc mockMvc;
	
	@Autowired
	private LessonDayController lessonDayController;
	
	@Before
	public void setUp() throws Exception {
		mockMvc = standaloneSetup(lessonDayController).build();
	}
	
	@Test
	public void testPostLessonDay() throws Exception {
//		MvcResult result = mockMvc.perform(
//                post("/lesson-days")
//                .param("day", "금")
//                .param("startTime", "PM 5:0")
//                .param("endTime", "PM 7:0")
//                .param("lessonId", "1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
//                .andReturn();
//		
//		logger.info(result.getResponse().getContentAsString());

	}
}
