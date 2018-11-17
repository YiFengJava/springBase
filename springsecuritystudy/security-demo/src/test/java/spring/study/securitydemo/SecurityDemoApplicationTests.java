package spring.study.securitydemo;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.JsonPath;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@RunWith(SpringRunner.class)    //用SpringRunner来执行这个测试用例
@SpringBootTest       //声明是个测试
public class SecurityDemoApplicationTests {

//	伪造一个测试环境
	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Before
	public void setup(){
//		构建一个伪造测试环境
		mockMvc= MockMvcBuilders.webAppContextSetup(wac).build();
	}
	@Test
	public void contextLoads() {
	}

	@Test
	public void whenQuerySuccess() throws  Exception{
		//执行一个get请求
		//请求的content
		//期望的返回是成功的isOK
		String content = mockMvc.perform(MockMvcRequestBuilders.get("/user")
				.param("username", "dfsafd")
				.param("age", "18")
				.param("ageTo", "80")
				.param("xxx", "dfsafd")
				.param("size","15")
				.param("page","2")
				.param("sort","age,desc")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
				.andReturn().getResponse().getContentAsString();
		System.out.println(content);
//		jsonPath  github上
	}

	@Test
	public void whenGenInfoSuccess() throws  Exception{
		String tom = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(tom);
	}
	@Test
	public void whenGetInfoFail() throws  Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().is4xxClientError());
	}

	@Test
	public void  whenCreateSuccess() throws  Exception{
		String content="{\"username\":\"tom\",\"password\":null,\"brithday\":"+new Date().getTime()+"}";
		String contentAsString = mockMvc.perform(MockMvcRequestBuilders.post("/user")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	public void  whenUpdateSuccess() throws  Exception{
		Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		String content="{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"brithday\":"+date.getTime()+"}";
		String contentAsString = mockMvc.perform(MockMvcRequestBuilders.put("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
				.andReturn().getResponse().getContentAsString();
		System.out.println(contentAsString);
	}

	@Test
	public void  whenDeleteSuccess() throws  Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void whereUploadSuccess()throws  Exception{
		String file = mockMvc.perform(MockMvcRequestBuilders.multipart("/file")
				.file(new MockMultipartFile("file", "test.txt", "multipart/form-data", "helle upload".getBytes("UTF-8"))))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn().getResponse().getContentAsString();
		System.out.println(file.toString());
	}
}
