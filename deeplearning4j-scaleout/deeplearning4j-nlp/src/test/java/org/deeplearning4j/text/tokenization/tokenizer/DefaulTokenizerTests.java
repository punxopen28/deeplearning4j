/*
 *
 *  * Copyright 2015 Skymind,Inc.
 *  *
 *  *    Licensed under the Apache License, Version 2.0 (the "License");
 *  *    you may not use this file except in compliance with the License.
 *  *    You may obtain a copy of the License at
 *  *
 *  *        http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *    Unless required by applicable law or agreed to in writing, software
 *  *    distributed under the License is distributed on an "AS IS" BASIS,
 *  *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *    See the License for the specific language governing permissions and
 *  *    limitations under the License.
 *
 */

package org.deeplearning4j.text.tokenization.tokenizer;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;

import org.apache.commons.io.FileUtils;
import org.deeplearning4j.text.tokenization.tokenizerfactory.DefaultTokenizerFactory;
import org.deeplearning4j.text.tokenization.tokenizerfactory.TokenizerFactory;
import org.junit.Test;
import org.springframework.core.io.ClassPathResource;

public class DefaulTokenizerTests {
	
	
	@Test
	public void testDefaultTokenizer() throws Exception {
		String toTokenize = "Mary had a little lamb.";
		TokenizerFactory t = new DefaultTokenizerFactory();
		Tokenizer tokenizer = t.create(toTokenize);
		Tokenizer tokenizer2 = t.create(new ByteArrayInputStream(toTokenize.getBytes()));
		while(tokenizer.hasMoreTokens()) {
		   assertEquals(tokenizer.nextToken(),tokenizer2.nextToken());
		}
		
		
		ClassPathResource resource = new ClassPathResource("/reuters/5250");
		String str = FileUtils.readFileToString(resource.getFile());
		int stringCount = t.create(str).countTokens();
		int stringCount2 = t.create(resource.getInputStream()).countTokens();
		assertTrue(Math.abs(stringCount - stringCount2) < 2);
		
	}
	
	

}
