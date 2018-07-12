package br.ufsm.csi.seguranca.controller;


import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Funcionario;
import br.ufsm.csi.seguranca.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.net.ssl.SSLEngine;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static br.ufsm.csi.seguranca.Util.UtilVerifyRecaptcha.verify;

@Controller
public class UsuarioController {

    @Autowired
    private HibernateDAO hibernateDAO;
    private static  final String retiraScript = "<.*!?>";



    @Transactional
    @RequestMapping("login.html")
    public String login(String login, String senha, HttpSession session, Model model, HttpServletRequest request) {
        try {
            System.out.println("ta aqui no login");
            Map<String, Object> map = new HashMap<>();
            script(login);
            script(senha);

            map.put("login", login);
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            map.put("senha", md.digest(senha.getBytes("ISO-8859-1")));

            Collection funcionarios = hibernateDAO.listaObjetosEquals(Funcionario.class, map);

            String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
            boolean verify = verify(gRecaptchaResponse);
            System.out.println("Verify: "+verify);
            System.out.println("funciionarios: "+funcionarios);


            if ( verify && !(funcionarios == null || funcionarios.isEmpty())) {
                System.out.println("ta no if do login");
                session.invalidate();
                HttpSession nS = request.getSession();
                nS.setAttribute("funcionarioLogado", funcionarios.toArray()[0]);
                String titulo = null;
                String texto = null;

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

                return "hello";
            } else {
                System.out.println("Ta no Else");
                model.addAttribute("msgDoServidor", "acesso-negado");
                return "../../index";
            }
        }catch (Exception e){
            System.out.println("ERRO: "+e);
        }
        return "../../index";
    }

    public static String script(String html) {
        String retorno = html.replaceAll(retiraScript, "");
        return retorno;
    }

    @Transactional
    @RequestMapping("Hello.html")
    public String login(HttpSession session, Model model, HttpServletRequest request) {
        try {
            Map<String, Object> map = new HashMap<>();


            Collection funcionarios = hibernateDAO.listaObjetosEquals(Funcionario.class, map);


            if ( !(funcionarios == null || funcionarios.isEmpty())) {
                System.out.println("ta no if do login");

                session.setAttribute("funcionarioLogado", funcionarios.toArray()[0]);
                String titulo = null;
                String texto = null;

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

                return "hello";
            } else {
                System.out.println("Ta no Else");
                model.addAttribute("msgDoServidor", "acesso-negado");
                return "index";
            }
        }catch (Exception e){
            System.out.println("ERRO: "+e);
        }
        return "../../index";
    }

    @Transactional
    @RequestMapping("paginaListaFilhos.html")
    public String paginaListaFilhos(Model model) {
        Map<String, String> map = new HashMap<>();
        map.put("nomeFilho", "");
        Collection funcionarios = hibernateDAO.listaObjetos(Funcionario.class, map, null, null, false);
        model.addAttribute("funcionarios", funcionarios);
        return "listar";
    }

    @Transactional
    @RequestMapping("sair.priv")
    public String paginaListaFilhos(HttpSession session) {
        session.invalidate();
        return "../../index";
    }

//    @Transactional
//    @RequestMapping("cria-log.priv")
//    public String criaLog(Long idUsuario,
//                          Long idObjeto,
//                          String classe,
//                          @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm") Date dataHora) throws ClassNotFoundException {
//        Usuario usuario = (Usuario) hibernateDAO.carregaObjeto(Usuario.class, idUsuario);
//        Log log = new Log();
//        log.setClasse(Class.forName(classe));
//        log.setIdObjeto(idObjeto);
//        log.setDataHora(dataHora);
//        log.setUsuario(usuario);
//        hibernateDAO.criaObjeto(log);
//        return "log";
//    }
//
//    @Transactional
//    @RequestMapping("lista-usuarios.html")
//    public String listaUsuarios(Model model, String nome, String login) {
//        Map<String, String> m = new HashMap<>();
//        if (nome != null && !nome.isEmpty()) {
//            m.put("nome", nome);
//        }
//        if (login != null && !login.isEmpty()) {
//            m.put("login", login);
//        }
//        model.addAttribute("usuarios", hibernateDAO.listaObjetos(Usuario.class, m, null, null, false));
//        return "listaFuncionarios";
//    }

}
