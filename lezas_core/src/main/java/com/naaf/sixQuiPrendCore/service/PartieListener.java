package com.naaf.sixQuiPrendCore.service;

import java.util.EventListener;

public interface PartieListener extends EventListener {

	void nbJoueurComplete(String nomPartie);

}
