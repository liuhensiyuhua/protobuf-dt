/*
 * Copyright (c) 2011 Google Inc.
 *
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.google.eclipse.protobuf.scoping;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

import org.eclipse.xtext.naming.QualifiedName;
import org.junit.Test;

/**
 * Tests for <code>{@link QualifiedNames#addLeadingDot(QualifiedName)}</code>.
 *
 * @author alruiz@google.com (Alex Ruiz)
 */
public class QualifiedNames_addLeadingDot_Test {

  @Test public void should_add_leading_dot() {
    QualifiedName name = QualifiedName.create("jedis", "Luke");
    QualifiedName withLeadingDot = QualifiedNames.addLeadingDot(name);
    assertThat(withLeadingDot.toString(), equalTo(".jedis.Luke"));
  }

  @Test public void should_not_add_leading_dot_if_qualified_name_already_has_it() {
    QualifiedName name = QualifiedName.create("", "jedis", "Luke");
    QualifiedName withLeadingDot = QualifiedNames.addLeadingDot(name);
    assertThat(withLeadingDot.toString(), equalTo(".jedis.Luke"));
  }
}
