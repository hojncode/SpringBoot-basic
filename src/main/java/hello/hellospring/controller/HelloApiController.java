package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloApiController {

    @GetMapping("hello-string") // 브라우저 url 창.
    @ResponseBody
    public String HelloApi(@RequestParam("name") String name) {  // 브라우저 Url 의 ?name=
        return "hello " + name;
    }
}
