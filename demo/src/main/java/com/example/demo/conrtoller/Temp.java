    package com.example.demo.conrtoller;

    import com.example.demo.service.TempService;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.web.bind.annotation.GetMapping;
    import org.springframework.web.bind.annotation.RequestMapping;
    import org.springframework.web.bind.annotation.RestController;

    @RestController
    @RequestMapping("/")
    public class Temp {
        @Autowired
        private TempService tempService;
        @GetMapping("/")
        public void temp(){
            tempService.evaluate();
        }
    }
