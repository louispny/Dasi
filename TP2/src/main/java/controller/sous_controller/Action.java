/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.sous_controller;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author lpigny
 */
public abstract class Action {

    public Action() {
    }
    
    public abstract void executer(HttpServletRequest request);
}
