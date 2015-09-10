package paris.velocafe.marketplace.utils;

import java.io.IOException;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.faces.context.FacesContext;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import paris.velocafe.marketplace.domain.Args;
import paris.velocafe.marketplace.utils.Constantes.Marque;

public class CommonUtils {

	@SuppressWarnings("rawtypes")
	public static <E> boolean isNotNullOrEmpty(final E object) {
		boolean isNotNullOrEmpty = false;
		if (object != null) {
			if (object instanceof Collection) {
				isNotNullOrEmpty = ((Collection) object).size() > 0;
			} else {
				isNotNullOrEmpty = object.toString() != null && !Consts.EMPTY.equals(object.toString());
			}
		}
		return isNotNullOrEmpty;
	}

	public static <E> boolean isNullOrEmpty(final E object) {
		return !isNotNullOrEmpty(object);
	}

	public static java.sql.Date toSqlDate(final java.util.Date date) {
		return date != null ? new java.sql.Date(date.getTime()) : null;
	}

	public static java.util.Date toUtilDate(final int day, final int month, final int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		return calendar.getTime();
	}

	/**
	 * Renvoie la date au format java.sql.Date
	 */
	public static java.sql.Date toSqlDate(final int day, final int month, final int year) {
		return toSqlDate(toUtilDate(day, month, year));
	}

	/**
	 * Renvoie la date d'aujourd'hui au format java.sql.Date
	 */
	public static java.sql.Date toSqlDate() {
		Calendar calendar = Calendar.getInstance();
		return toSqlDate(calendar.getTime());
	}

	/**
	 * Renvoie l'année d'une date donnée
	 */
	public static int getYear(final Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar.get(Calendar.YEAR);
	}

	/**
	 * Renvoie l'année de la date d'aujourd'hui
	 */
	public static int getYear() {
		return getYear(toSqlDate());
	}

	/**
	 * Renvoie une url complete à partir de la page xhtml et d'une map des
	 * paramètres valeurs
	 */
	public static <E> void toUrlAndParams(final XhtmlPages xhtmlPage, final Collection<Args<E>> params) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(xhtmlPage);
		String separator = Consts.QUOTE;
		if (params != null) {
			for (Args<?> param : params) {
				if (param != null) {
					stringBuilder.append(separator);
					separator = Consts.AND;
					stringBuilder.append(param.getParam()).append(Consts.EQUALS);
					stringBuilder.append(toString(param.getValues()));
				}
			}
		}
		String path = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
		String url = stringBuilder.toString();
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(path + url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void toUrl(final XhtmlPages xhtmlPage) {
		toUrlAndParams(xhtmlPage, null);
	}

	/**
	 * Converti une liste de valeurs en String<br/>
	 * Exemple [v1,v2,v3]
	 */
	public static <E> String toString(final Collection<E> liste) {
		StringBuilder stringBuilder = new StringBuilder().append(Consts.LEFT_BRAKET);
		String separator = Consts.EMPTY;
		if (liste != null && liste.size() > 0) {
			for (Object object : liste) {
				stringBuilder.append(separator).append(object.toString());
				separator = Consts.COMMA;
			}
		}
		stringBuilder.append(Consts.RIGHT_BRACKET);
		return stringBuilder.toString();
	}

	/**
	 * Converti une liste de valeurs en Set<br/>
	 * Exemple [v1,v2,v3]
	 */
	public static Set<String> toStringSet(final String values) {
		Set<String> set = new HashSet<String>();
		String[] strings = toStringTable(values);
		if (strings != null) {
			for (String string : strings) {
				set.add(string);
			}
		}
		return set;
	}

	/**
	 * 
	 * Converti une liste de valeurs en Table<br/>
	 * Exemple [v1,v2,v3]
	 */
	public static String[] toStringTable(final String values) {
		return values.replace(Consts.LEFT_BRAKET, Consts.EMPTY).replace(Consts.RIGHT_BRACKET, Consts.EMPTY).split(Consts.COMMA);
	}

	/**
	 * Converti les attributs du contexte en liste d'Args
	 */
	public static Set<Args<String>> toMap(Map<String, String> attributes) {
		Set<Args<String>> args = new HashSet<Args<String>>();
		if (CommonUtils.isNotNullOrEmpty(attributes)) {
			for (Entry<String, String> attribute : attributes.entrySet()) {
				if (attribute.getKey() instanceof String && attribute.getValue() instanceof String) {
					args.add(new Args<String>((String) attribute.getKey(), CommonUtils.toStringTable((String) attribute.getValue())));
				}
			}
		}
		return args;
	}

	public static <E> Set<E> toSet(E... es) {
		Set<E> set = new HashSet<E>();
		if (es != null) {
			for (E e : es) {
				set.add(e);
			}
		}
		return set;
	}

	/**
	 * Convertit une string du type [1] en 1
	 */
	public static Long parseLong(final String string) {
		Long l;
		try {
			String s = CommonUtils.isNotNullOrEmpty(string) ? string.replace(Consts.LEFT_BRAKET, Consts.EMPTY).replace(Consts.RIGHT_BRACKET, Consts.EMPTY) : null;
			l = CommonUtils.isNotNullOrEmpty(s) ? Long.parseLong(s) : null;
		} catch (NumberFormatException e) {
			return null;
		}
		return l;
	}

	public static String toFullName(final String surname, final String name) {
		StringBuilder stringBuilder = new StringBuilder();
		if (CommonUtils.isNotNullOrEmpty(surname)) {
			stringBuilder.append(surname);
		}
		if (CommonUtils.isNotNullOrEmpty(name)) {
			if (CommonUtils.isNotNullOrEmpty(surname)) {
				stringBuilder.append(Consts.SPACE);
			}
			stringBuilder.append(name);
		}
		return stringBuilder.toString();
	}

	public static Blob toBlob(final byte[] bytes) {
		try {
			return new SerialBlob(bytes);
		} catch (SerialException e) {
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static byte[] toBytes(final Blob blob) {
		int blobLength;
		try {
			blobLength = (int) blob.length();
			return blob.getBytes(1, blobLength);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static <E> Map<String, String> collectionToMap(final E[] collection) {
		Map<String, String> map = new HashMap<String, String>();
		for (E e : collection) {
			map.put(e.toString(), e.toString());
		}
		return map;
	}

	/**
	 * Constantes locales utiles
	 *
	 */
	interface Consts {
		String EQUALS = "=";
		String AND = "&";
		String QUOTE = "?";
		String LEFT_BRAKET = "[";
		String RIGHT_BRACKET = "]";
		String COMMA = ",";
		String EMPTY = "";
		String SPACE = " ";
	}

}
