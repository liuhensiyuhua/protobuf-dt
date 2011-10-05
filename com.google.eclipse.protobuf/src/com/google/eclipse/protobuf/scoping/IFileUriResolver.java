/*
 * Copyright (c) 2011 Google Inc.
 *
 * All rights reserved. This program and the accompanying materials are made available under the terms of the Eclipse
 * Public License v1.0 which accompanies this distribution, and is available at
 *
 * http://www.eclipse.org/legal/epl-v10.html
 */
package com.google.eclipse.protobuf.scoping;

import static org.eclipse.xtext.util.Strings.isEmpty;

import com.google.eclipse.protobuf.scoping.IFileUriResolver.NullFileUriResolver;
import com.google.inject.ImplementedBy;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;

import java.io.File;

/**
 * Resolves "import" URIs.
 *
 * @author alruiz@google.com (Alex Ruiz)
 */
@ImplementedBy(NullFileUriResolver.class)
public interface IFileUriResolver {

  /**
   * Resolves the given 'import' URI.
   * @param importUri the 'import' URI.
   * @param declaringResource the resource declaring the import.
   * @return the resolved URI, or {@code importUri} if resolution was not successful.
   */
  String resolveUri(String importUri, Resource declaringResource);

  class NullFileUriResolver implements IFileUriResolver {
    public String resolveUri(String importUri, Resource declaringResource) {
      // default implementation that simply checks that the file exists in the file system.
      URI uri = URI.createURI(importUri);
      if (!isEmpty(uri.scheme())) return importUri; // already resolved.
      File file = new File(importUri);
      if (!file.exists()) throw new IllegalArgumentException("File: " + importUri + " does not exist.");
      return file.toURI().toString();
    }
  }
}