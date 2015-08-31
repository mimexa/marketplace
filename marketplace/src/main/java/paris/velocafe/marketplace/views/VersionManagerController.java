package paris.velocafe.marketplace.views;

import java.io.Serializable;
import static paris.velocafe.marketplace.domain.Version.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import paris.velocafe.marketplace.domain.Version;
import paris.velocafe.marketplace.utils.CommonUtils;

@SuppressWarnings("serial")
@Named
@SessionScoped
public class VersionManagerController implements Serializable {

	boolean isRendered(String id) {
		boolean isRendered = true;
		if (CommonUtils.isNotNullOrEmpty(id)) {
			Set<Version> supportedVersion = Consts.isRendered.get(id);
			isRendered = supportedVersion != null ? supportedVersion.contains(Consts.version) : true;
		}
		return isRendered;
	}

	private static class Consts {
		public static final Version version = DEV;
		public static final Map<String, Set<Version>> isRendered = new HashMap<String, Set<Version>>();
		static {
			isRendered.put("administrationMenu", CommonUtils.toSet(DEV));
			isRendered.put("pratiqueMenu", CommonUtils.toSet(DEV, PROD));
			isRendered.put("neufMenu", CommonUtils.toSet(DEV));
			isRendered.put("occasionsMenu", CommonUtils.toSet(DEV));
			isRendered.put("view", CommonUtils.toSet(DEV));
			isRendered.put("reparationMenu", CommonUtils.toSet(DEV));
			isRendered.put("mesureMenu", CommonUtils.toSet(DEV, PROD));
			isRendered.put("aLaCarte", CommonUtils.toSet(DEV));
			isRendered.put("partenaires", CommonUtils.toSet(DEV));
			isRendered.put("footer", CommonUtils.toSet(DEV));
		}
	}

}
