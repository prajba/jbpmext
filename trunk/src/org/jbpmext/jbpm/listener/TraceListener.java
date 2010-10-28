package org.jbpmext.jbpm.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;

/**
 * 
 activeRects : {// 当前�?��状�? rects : [{name:'任务1',paths:[]}] }, historyRects :
 * {// 历史�?��状�? rects : [{name:'�?��',paths:['TO 分支']},{name:'分支',paths:['TO
 * 任务1']},{name:'任务1',paths:[]}] }
 * 
 * @author hyli
 * 
 */
public class TraceListener implements EventListener {
	public static String VAR_TRANCE = "myflow_trance";

	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		Map<String, Map<String, List<Map<String, Object>>>> tmap = (Map<String, Map<String, List<Map<String, Object>>>>) execution
				.getVariable(VAR_TRANCE);
		switch (type) {
		case 0:// 初始�?
			tmap = new HashMap<String, Map<String, List<Map<String, Object>>>>();
			tmap.put("activeRects",
					new HashMap<String, List<Map<String, Object>>>());
			tmap.put("historyRects",
					new HashMap<String, List<Map<String, Object>>>());
			tmap.get("activeRects").put("rects",
					new ArrayList<Map<String, Object>>());
			tmap.get("historyRects").put("rects",
					new ArrayList<Map<String, Object>>());
			execution.setVariable(VAR_TRANCE, tmap);
			break;
		case 1:// �?��状�?
			Map<String, Object> act = new HashMap<String, Object>();
			act.put("name", this.activity);
			act.put("paths", new ArrayList<String>());
			tmap.get("activeRects").get("rects").add(act);
			break;
		case 2:// 结束状�?
			List<Map<String, Object>> acts = tmap.get("activeRects").get(
					"rects");
			for (int i = 0; i < acts.size(); i++) {
				if (acts.get(i).get("name").equals(this.activity)) {
					acts.remove(i);
					break;
				}
			}
			break;
		case 3:// 转换
			List<Map<String, Object>> hacts = tmap.get("historyRects").get(
					"rects");
			boolean isfound = false;
			for (int i = 0; i < hacts.size(); i++) {
				if (hacts.get(i).get("name").equals(this.activity)) {
					List<String> trans = (List<String>) hacts.get(i).get(
							"paths");
					trans.add(this.transition);
					isfound = true;
					break;
				}
			}
			if (!isfound) {
				Map<String, Object> hact = new HashMap<String, Object>();
				hact.put("name", this.activity);
				List<String> htrans = new ArrayList<String>();
				htrans.add(this.transition);
				hact.put("paths", htrans);
				hacts.add(hact);
			}
			break;
		}

	}

	int type;
	String activity;
	String transition;
}
