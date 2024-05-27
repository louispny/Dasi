package controller.sous_controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class DeconnecterAction extends Action {
        @Override
        public void executer(HttpServletRequest request) {; 
            HttpSession session = request.getSession(true);
            session.invalidate();
            request.setAttribute("deconnecter", true) ;
        }
}
