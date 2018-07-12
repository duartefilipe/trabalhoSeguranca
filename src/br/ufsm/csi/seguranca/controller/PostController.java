package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.Util.UtilLog;
import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Funcionario;
import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PostController {

    @Autowired
    private HibernateDAO hibernateDAO;

    @Transactional
    @RequestMapping("criaPost.priv")
    public String criaPost(Post post, HttpSession session){
        try {

            if(post.getIdpost() == null) {
                hibernateDAO.criaObjeto(post);
                return "redirect: listaPosts.priv";
            }else{
                Post post1 = (Post) hibernateDAO.carregaObjeto(Post.class, post.getIdpost());
                post1.setTitulo( post.getTitulo() );
                post1.setTexto( post.getTexto() );
                return "redirect: listaPosts.priv";
            }

        }catch(Exception e){
            System.out.println("ERRO no cria post que vai pro hello: "+e);
        }
        return "redirect: listaPosts.priv";
    }


    @Transactional
    @RequestMapping("listaPosts.priv")
    public String listaPosts(Model model, String titulo, String texto) {
        Map<String, String> m = new HashMap<>();
        if (titulo != null && !titulo.isEmpty()) {
            m.put("titulo", titulo);
            System.out.println("titulo: "+titulo);
        }
        if (texto != null && !texto.isEmpty()) {
            m.put("texto", texto);
            System.out.println("texto: "+texto);
        }
        System.out.println("Lista posts");


        model.addAttribute("posts", hibernateDAO.listaObjetos(Post.class, m, null, null, false));
        return "listaPosts";
    }

    @Transactional
    @RequestMapping(value = "removePost.priv", method = RequestMethod.GET)
    public String removePost(Long id, HttpSession session){
        try {
            Post post = (Post) hibernateDAO.carregaObjeto(Post.class, id);
            UtilLog.getInstance().gerarLog(Post.class, post.getIdpost(), Log.Tipo.Delete, hibernateDAO, session);
            hibernateDAO.removeObjeto(post);
            return "redirect: listaPosts.priv";
        }catch (Exception e){
            System.out.println("ERRO: "+e);
        }
        return "redirect: listaPosts.priv";
    }

    @Transactional
    @RequestMapping(value = "removePost2.priv", method = RequestMethod.GET)
    public String removePost2(Long id, HttpSession session){
        try {
            Post post = (Post) hibernateDAO.carregaObjeto(Post.class, id);
            UtilLog.getInstance().gerarLog(Post.class, post.getIdpost(), Log.Tipo.Delete, hibernateDAO, session);
            hibernateDAO.removeObjeto(post);
            return "redirect: Hello.html";
        }catch (Exception e){
            System.out.println("ERRO: "+e);
        }
        return "redirect: Hello.html";
    }


}
