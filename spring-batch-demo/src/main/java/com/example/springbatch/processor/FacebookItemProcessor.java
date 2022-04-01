package com.example.springbatch.processor;

import com.example.springbatch.model.Facebook;

import org.springframework.batch.item.ItemProcessor;

public class FacebookItemProcessor implements ItemProcessor<Facebook, Facebook> {

	@Override
	public Facebook process(final Facebook facebook) throws Exception {
		// final String firstName = facebook.getFirstName().toUpperCase();
		// final String lastName = facebook.getLastName().toUpperCase();

		// final Person transformedPerson = new Person(firstName, lastName);

		// log.info("Converting (" + person + ") into (" + transformedPerson + ")");

		// return transformedPerson;
		return facebook;
	}

}