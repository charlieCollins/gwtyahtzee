package com.totsp.gwt.yahtzee.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Yahtzee implements EntryPoint {

  /**
   * This is the entry point method.
   */
  public void onModuleLoad() {
  YahtzeeWidget yWidget = new YahtzeeWidget();
  RootPanel.get("yahtzeeWidget").add(yWidget);
  }
}
