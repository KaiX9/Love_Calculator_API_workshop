package sg.edu.nus.iss.lovecalculator.controller;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sg.edu.nus.iss.lovecalculator.model.Lc;
import sg.edu.nus.iss.lovecalculator.service.LcService;

@Controller
@RequestMapping(path="/love")
public class LcController {

    @Autowired
    private LcService lSvc;

    @GetMapping
    public String getLove(@RequestParam(required=true) String fname,
    @RequestParam(required=true) String sname, Model m) throws IOException {
        Optional<Lc> l = lSvc.getResult(fname, sname);
        Lc lc = l.get();
        lSvc.save(lc);
        m.addAttribute("love", l.get());
        return "love";
    }
    
}
