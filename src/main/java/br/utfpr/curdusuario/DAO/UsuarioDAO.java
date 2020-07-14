/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.utfpr.curdusuario.DAO;

import br.utfpr.curdusuario.entity.Usuario;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class UsuarioDAO {
    protected static final ArrayList<Usuario> lUsuario = new ArrayList<>();
    protected final SessionFactory sessionFactory;
    
    public UsuarioDAO() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }
    
    public void save(Usuario usuario) {
        
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(usuario);
        session.getTransaction().commit();
        session.close();
    }
    
    public List<Usuario> getList() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        List<Usuario> result = session.createQuery("from Usuario order by login asc").list();
        session.getTransaction().commit();
        session.close();
        
        return result;
    }
    
    public void delete(Integer id) {
        Usuario user = getUsuarioById(id);
        if (user != null) {
            lUsuario.remove(user);
        }
    }
    
    public void update(Usuario entity, Integer id) {
        Usuario user = getUsuarioById(id);
        user.setLogin(entity.getLogin());
        user.setNome(entity.getNome());
        user.setSenha(entity.getSenha());
        user.setIdade(entity.getIdade());
    }
    
    public Usuario getUsuarioById(Integer id) {
        for(Usuario user : lUsuario) {
            if(id == user.getId()) {
                return user;
            }
        }
        return null;
    }
}