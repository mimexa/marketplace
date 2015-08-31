package paris.velocafe.marketplace.dao;

import java.io.Serializable;
import static paris.velocafe.marketplace.domain.Produit.Props.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import paris.velocafe.marketplace.domain.Operator;
import paris.velocafe.marketplace.domain.SqlParams;
import paris.velocafe.marketplace.entity.ProduitDb;
import paris.velocafe.marketplace.rowmapper.AbstractRowMapper;
import paris.velocafe.marketplace.rowmapper.ProduitDbRowMapper;

@SuppressWarnings("serial")
@Stateless
public class ProduitDaoImpl implements Serializable {

	private static final AbstractRowMapper<ProduitDb> rowMapper = new ProduitDbRowMapper();

	@Resource(mappedName = "jdbc/velocafeDB")
	private DataSource ds;

	public ProduitDb find(final Long id) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
		Set<SqlParams<?>> params = new HashSet<SqlParams<?>>();
		params.add(new SqlParams<Long>(idProduit, Operator.EQUALS, id));
		return DaoUtils.queryForSingleResult(jdbcTemplate, params, rowMapper);
	}

	public List<ProduitDb> findAll() {
		return DaoUtils.queryAll(new JdbcTemplate(ds), rowMapper);
	}

	public List<ProduitDb> findList(final Set<SqlParams<?>> args) {
		return DaoUtils.query(new JdbcTemplate(ds), args, rowMapper);
	}

	public int update(final ProduitDb produitDb) {
		return DaoUtils.update(new JdbcTemplate(ds), produitDb, rowMapper);
	}

	public Long getNewAvaibleId() {
		return DaoUtils.getNewAvaibleId(new JdbcTemplate(ds), rowMapper);
	}

	public void insert(ProduitDb produitDb) {
		DaoUtils.insert(new JdbcTemplate(ds), produitDb, rowMapper);
	}
}
