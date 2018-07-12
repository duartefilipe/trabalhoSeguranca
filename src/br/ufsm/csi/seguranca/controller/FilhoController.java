package br.ufsm.csi.seguranca.controller;

import br.ufsm.csi.seguranca.Util.UtilLog;
import br.ufsm.csi.seguranca.dao.HibernateDAO;
import br.ufsm.csi.seguranca.model.Filho;
import br.ufsm.csi.seguranca.model.Log;
import br.ufsm.csi.seguranca.model.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static br.ufsm.csi.seguranca.Util.UtilLog.gerarLog;

@Controller
public class FilhoController {

    @Autowired
    private HibernateDAO hibernateDAO;
    private static  final String retiraScript = "<.*!?>";



    @Transactional
    @RequestMapping("listaFilhos.priv")
    public String gerenciarFilhos(Model model, HttpSession session) {
        try {
            Map<String, String> map = new HashMap<>();
            Map<String, String> map2 = new HashMap<>();
            map.put("nomeFilho", "");
            map2.put("nome", "");
            Collection<Filho> filhos = hibernateDAO.listaObjetos(Filho.class, map, null, null, false);
            Collection<Pais> pais = hibernateDAO.listaObjetos(Pais.class, map2, null, null, false);
            for (Filho filho : filhos) {
                gerarLog(Filho.class, filho.getId(), Log.Tipo.Read, hibernateDAO, session);
            }
            model.addAttribute("filhos", filhos);
            model.addAttribute("pais", pais);
            return "listaFilhos";
        }catch(Exception e){
            System.out.println("ERRO: "+e);
        }
        return "listaFilhos";
    }

    @Transactional
    @RequestMapping("paginaCriaFilho.priv")
    public String paginaCriaFilho(Model model) {
        try {
            Map<String, String> map = new HashMap<>();
            map.put("nome", "");
            Collection pais = hibernateDAO.listaObjetos(Pais.class, map, null, null, false);
            model.addAttribute("pais", pais);
            return "listaFilhos";
        }catch (Exception e){
            System.out.println("ERRO: "+e);
        }
        return "listaFilhos";
    }

    public static String script(String html) {
        String retorno = html.replaceAll(retiraScript, "");
        return retorno;
    }

    @Transactional
    @RequestMapping(value = "editaFilho.priv", method = RequestMethod.GET)
    public String editaFilho(Model model, Long id) {
        Filho filho = (Filho) hibernateDAO.carregaObjeto(Filho.class, id);

        Map<String, String> map = new HashMap<>();
        map.put("nome", "");
        Collection paiss = hibernateDAO.listaObjetos(Pais.class, map, null, null, false);

        model.addAttribute("filho", filho);
        model.addAttribute("paiss", paiss);
        return "editaFilho";
    }

    @Transactional
    @RequestMapping("criaFilho.priv")
    public String criaFilho(Filho filho, Long id, HttpSession session) {
        try {
            Pais pais = (Pais) hibernateDAO.carregaObjeto(Pais.class, id);
            filho.setPais(pais);


                filho.setNomeFilho(script(filho.getNomeFilho()));
                hibernateDAO.criaObjeto(filho);
                UtilLog.getInstance().gerarLog(Filho.class, filho.getId(), Log.Tipo.Create, hibernateDAO, session);
                Filho filho1 = (Filho) hibernateDAO.carregaObjeto(Filho.class, filho.getId());
                filho1.setNomeFilho(script(filho.getNomeFilho()));
                filho1.setPais(filho.getPais());
                UtilLog.getInstance().gerarLog(Filho.class, filho.getId(), Log.Tipo.Update, hibernateDAO, session);

            return "redirect:listaFilhos.priv";
        }catch (Exception e){
            System.out.println("ERRO no cadastro filho: "+e);
        }
        return "redirect:listaFilhos.priv";
    }


    @Transactional
    @RequestMapping(value = "removeFilhos.priv", method = RequestMethod.GET)
    public String removePost(Long id, HttpSession session){
        try {
            Filho filho = (Filho) hibernateDAO.carregaObjeto(Filho.class, id);
            UtilLog.getInstance().gerarLog(Filho.class, filho.getId(), Log.Tipo.Delete, hibernateDAO, session);
            hibernateDAO.removeObjeto(filho);
            return "redirect: listaFilhos.priv";
        }catch (Exception e){
            System.out.println("ERRO: "+e);
        }
        return "redirect: listaFilhos.priv";
    }
}
