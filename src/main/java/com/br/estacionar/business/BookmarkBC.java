package com.br.estacionar.business;

import java.util.List;

import com.br.estacionar.entity.Bookmark;
import com.br.estacionar.persistence.BookmarkDAO;

import br.gov.frameworkdemoiselle.lifecycle.Startup;
import br.gov.frameworkdemoiselle.stereotype.BusinessController;
import br.gov.frameworkdemoiselle.template.DelegateCrud;
import br.gov.frameworkdemoiselle.transaction.Transactional;

@BusinessController
public class BookmarkBC extends DelegateCrud<Bookmark, Long, BookmarkDAO> {

	private static final long serialVersionUID = 1L;

	@Startup
	@Transactional
	public void load() {
		if (findAll().isEmpty()) {
			insert(new Bookmark("Portal", "http://www.frameworkdemoiselle.gov.br"));
			insert(new Bookmark("Documentação", "http://demoiselle.sourceforge.net/docs/framework/reference"));
		}
	}

	public List<Bookmark> find(String filter) {
		return getDelegate().find(filter);
	}
}
