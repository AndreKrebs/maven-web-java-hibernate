/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.curdusuario.controller;

import br.utfpr.curdusuario.DAO.UsuarioDAO;
import br.utfpr.curdusuario.entity.Usuario;
import java.io.Serializable;
import java.util.List;
import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.transaction.SystemException;

/**
 *
 * @author andre
 */
@ManagedBean
@ViewScoped
public class UsuarioController implements Serializable {
    private Integer id;
    private Usuario usuario;
    private final UsuarioDAO usuarioDao;
    
    private static final String LOGIN_EXISTE = "Login já existe, altere o valor no campo `Login`";

    public UsuarioController() {
        this.usuario = new Usuario();
        this.usuarioDao = new UsuarioDAO();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public List<Usuario> getList() {
        return usuarioDao.getList();
    }
    
    public Integer getId() {
        return id;
    }
    
    public String save() throws SystemException {
        if (usuario.getId() == null || usuario.getId() == 0) {
            if (usuarioDao.checkLoginExists(usuario.getLogin())) {
                FacesContext.getCurrentInstance().addMessage("i-login", new FacesMessage(LOGIN_EXISTE));
                return null;
            } else {
                usuarioDao.save(usuario);
            }
        } else {
            if (usuarioDao.checkLoginExistsAnotherId(usuario.getId(), usuario.getLogin())) {
                FacesContext.getCurrentInstance().addMessage("i-login", new FacesMessage(LOGIN_EXISTE));
                return null;
            } else {
                usuarioDao.update(usuario);
            }
        }
        
        if (FacesContext.getCurrentInstance().getMessages() == null) {
            id = null;
            usuario = new Usuario();
        }
        
        return "index.xhtml?faces-redirect=false";
    }
    
    public String delete(Integer id) {
        usuarioDao.delete(id);
        return "index.xhtml?faces-redirect=true";
    }
    
    public String edit(Integer id) {
        usuario = usuarioDao.getDisciplinaById(id);
        this.id = id;
        return "index.xhtml";
    }
    
}
