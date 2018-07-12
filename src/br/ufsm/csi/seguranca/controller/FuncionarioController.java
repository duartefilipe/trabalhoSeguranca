package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.Util.UtilLog;
import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Funcionario;
import br.ufsm.csi.seguranca.model.Log;
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
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Controller
public class FuncionarioController {

    @Autowired
    private HibernateDAO hibernateDAO;

    private static  final String retiraScript = "<.*!?>";

    public static String script(String html) {
        String retorno = html.replaceAll(retiraScript, "");
        return retorno;
    }

    @Transactional
    @RequestMapping("criaFuncionario.priv")
    public String criaFuncionario(Funcionario funcionario, String senhaStr, HttpSession session) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            funcionario.setSenha(md.digest(senhaStr.getBytes("ISO-8859-1")));
            if(funcionario.getId() == null) {
                funcionario.setNome(script(funcionario.getNome()));
                funcionario.setLogin(script(funcionario.getLogin()));
                hibernateDAO.criaObjeto(funcionario);
                return "redirect: listaFuncionarios.priv";
            }else{
                Funcionario funcionario1 = (Funcionario) hibernateDAO.carregaObjeto(Funcionario.class, funcionario.getId());
                funcionario1.setNome( script(funcionario.getNome()) );
                funcionario1.setLogin( script(funcionario.getLogin()) );
                funcionario1.setSenha( funcionario.getSenha() );
                return "redirect: listaFuncionarios.priv";
            }

        }catch(Exception e){
            System.out.println("ERRO no cria funcionario que vai pro hello: "+e);
        }
        return "redirect: listaFuncionarios.priv";

    }


    @Transactional
    @RequestMapping("listaFuncionarios.priv")
    public String listaUsuarios(Model model, String nome, String login) {
        try {
            Map<String, String> m = new HashMap<>();
            if (nome != null && !nome.isEmpty()) {
                m.put("nome", nome);
            }
            if (login != null && !login.isEmpty()) {
                m.put("login", login);
            }

            model.addAttribute("funcionarios", hibernateDAO.listaObjetos(Funcionario.class, m, null, null, false));
            return "listaFuncionarios";
        }catch(Exception e){
            System.out.println("ERRO: ");
        }
        return "listaFuncionarios";
    }

    @RequestMapping("logout.html")
    public String logout(HttpSession session){
        session.invalidate();//pega a sessao no parametro destroi ela
        return "../../index";
    }

    @Transactional
    @RequestMapping(value = "removeFuncionario.priv", method = RequestMethod.GET)
    public String removeFuncionario(Long id, HttpSession session){
        try {
            Funcionario funcionario = (Funcionario) hibernateDAO.carregaObjeto(Funcionario.class, id);
            UtilLog.getInstance().gerarLog(Funcionario.class, funcionario.getId(), Log.Tipo.Delete, hibernateDAO, session);
            hibernateDAO.removeObjeto(funcionario);
            return "redirect: listaFuncionarios.priv";
        }catch (Exception e){
            System.out.println("ERRO: "+e);
        }
        return "redirect: listaFuncionarios.priv";
    }

    @Transactional
    @RequestMapping(value = "editaFuncionario.priv", method = RequestMethod.GET)
    public String editaFuncionario(Model model, Long id) {

        Funcionario funcionario = (Funcionario) hibernateDAO.carregaObjeto(Funcionario.class, id);
        Map<String, String> map = new HashMap<>();
        map.put("nome", "");
        map.put("login", "");
        model.addAttribute("funcionario", funcionario);
        return "editaFuncionarios";
    }


}
