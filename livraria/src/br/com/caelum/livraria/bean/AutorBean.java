package br.com.caelum.livraria.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.caelum.livraria.dao.DAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;

@ManagedBean
@ViewScoped
public class AutorBean {

	private Autor autor = new Autor();
	private Integer autorId;

	public void carregarPelaId() {
		this.autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
	}

	public RedirectView gravar() {
//		System.out.println("Gravando autor " + this.autor.getNome());

		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}

		this.setAutor(new Autor());
		return new RedirectView("livro");
	}

	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}

	public void remover(Autor autor) {
		try {
			new DAO<Autor>(Autor.class).remove(autor);
		} catch (Exception e) {
			new Exception("N�o foi possivel remover Autor");
		}
	}

	public void carregar(Autor autor) {
		this.autor = autor;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
}
