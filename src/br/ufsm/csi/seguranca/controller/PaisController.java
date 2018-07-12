package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.Util.UtilLog;
import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class PaisController {


    @Autowired
    private HibernateDAO hibernateDAO;

    private static  final String retiraScript = "<.*!?>";

    public static String script(String html) {
        String retorno = html.replaceAll(retiraScript, "");
        return retorno;
    }

    @Transactional
    @RequestMapping("criaPais.priv")
    public String criaPais(Pais pais, HttpSession session, HttpServletRequest rq){
        try {
            System.out.println("Nome pais: "+pais.getNome());
            HttpSession sessao = rq.getSession(true);
            pais.setNome(script(pais.getNome()));
            hibernateDAO.criaObjeto(pais);

        }catch(Exception e){
            System.out.println("ERRO no cria pais que vai pro hello: "+e);
        }
        return "redirect: listaPais.priv";
    }


    @Transactional
    @RequestMapping("listaPais.priv")
    public String listaPais(Model model, String nome) {
        try {
            Map<String, String> m = new HashMap<>();
            if (nome != null && !nome.isEmpty()) {
                m.put("nome", nome);
            }

            System.out.println("Lista pais");
            System.out.println("nome pais: " + nome);
            model.addAttribute("pais", hibernateDAO.listaObjetos(Pais.class, m, null, null, false));
            return "listaPais";
        }catch(Exception e){
            System.out.println("ERRO: "+e);
        }
        return "listaPais";
    }

    @Transactional
    @RequestMapping(value = "removePais.priv", method = RequestMethod.GET)
    public String removePais(Long id, HttpSession session){
        try {
            Pais pais = (Pais) hibernateDAO.carregaObjeto(Pais.class, id);
            UtilLog.getInstance().gerarLog(Pais.class, pais.getId(), Log.Tipo.Delete, hibernateDAO, session);
            hibernateDAO.removeObjeto(pais);
            return "redirect: listaPais.priv";
        }catch (Exception e){
            System.out.println("ERRO: "+e);
        }
        return "redirect: listaPais.priv";
    }



}
