package personal.ysity.concurrency.example.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/redis")
public class RedisController {
    @Autowired
    private RedisClients redisClients;

    @RequestMapping("/set")
    @ResponseBody
    public String  set(@RequestParam("k") String k ,@RequestParam("v") String v) throws Exception {
        redisClients.set(k,v);
        return "success set";
    }

    @RequestMapping("/get")
    @ResponseBody
    public String get(@RequestParam("k") String k ) throws Exception {
        return redisClients.get(k);
    }
}
