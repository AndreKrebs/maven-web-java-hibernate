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
import javax.faces.bean.ManagedBean;
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
        if (id == null || id == 0) {
            usuarioDao.save(usuario);
        } else {
            usuarioDao.update(usuario, id);
        }
        
        id = null;
        usuario = new Usuario();
        return "index.xhtml";
    }
    
}
