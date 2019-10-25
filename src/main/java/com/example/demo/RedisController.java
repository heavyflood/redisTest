package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedisController {

	@Autowired
    RedisTemplate<String, Object> redisTemplate;

	@RequestMapping("/hash")
	public String setHash() throws Exception {
		
		HashOperations<String, String, String> hop = redisTemplate.opsForHash();
		
        String empKey01 = "heavyflood";
        String empKey02 = "jiwan";
        
        hop.put(empKey01, "name", "heavyflood");
        hop.put(empKey01, "age", "32");
        hop.put(empKey01, "id", "01");

        hop.put(empKey02, "name", "jiwan");
        hop.put(empKey02, "age", "42");
        hop.put(empKey02, "id", "02");
		
		int cnt = hop.entries(empKey01).size() + hop.entries(empKey02).size();
		
		System.out.println(">>>>>>> hash operation result :"+cnt);
		
		return String.valueOf(cnt);
		
	}

	@RequestMapping("/list")
	public String setList() throws Exception {
		
		ListOperations<String, Object> lop = redisTemplate.opsForList();
		
		lop.rightPush("list:test:2192010", "heavyflood");
		lop.rightPush("list:test:2192010", "42");
		lop.rightPush("list:test:2192010", "2192010");
		
		System.out.println(">>>>>>> list operation result :"+lop.leftPop("list:test:2192010").toString());
		return lop.leftPop("list:test:2192010").toString();
	}
	
	@RequestMapping("/val")
	public String setVal() throws Exception {
		
		ValueOperations<String, Object> vop = redisTemplate.opsForValue();
		
		vop.set("2192010", "heavyflood");
		vop.set("2192012", "jiwan");
		
		//System.out.println(">>>>>>> value operation result :"+vop.get("val:test:2192010").toString());
		String result = (String) vop.get("2192010");
		return result;
	}
}
