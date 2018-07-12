package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Funcionario;
import br.ufsm.csi.seguranca.model.Post;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.hibernate.query.QueryProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by cpol on 22/05/2017.
 */
@Controller
public class HelloController {

    @Autowired
    private HibernateDAO hibernateDAO;


    @RequestMapping("hello.priv")
    public String hello() {
        return "hello";
    }

    @RequestMapping("irlogin.html")
    public String login() {

        return "login";
    }


}
