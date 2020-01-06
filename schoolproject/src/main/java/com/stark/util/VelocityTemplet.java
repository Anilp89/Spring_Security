package com.stark.util;

import java.io.File;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class VelocityTemplet {
	// file path
	@Value("${velocity}")
	public static String VELOCITY_PATH;

	public static Writer getVelocity() {
		/* first, get and initialize an engine */
		VelocityEngine ve = new VelocityEngine();
		System.out.println("-------------------->" + VELOCITY_PATH);
		ve.setProperty(RuntimeConstants.FILE_RESOURCE_LOADER_PATH, "./src/main/resources/etc/");
		ve.init();

		/* create a context and add data */
		VelocityContext context = new VelocityContext();
		context.put("price1", "hello");
		context.put("price", "World");
		context.put("name", "test");
		context.put("value", "4356");

		/* next, get the Template */
		Template t = ve.getTemplate("test.vm");

		StringWriter writer = new StringWriter();
		t.merge(context, writer);

		// System.out.println(writer);
		// System.out.println( writer.toString() );
		return writer;
	}
}
