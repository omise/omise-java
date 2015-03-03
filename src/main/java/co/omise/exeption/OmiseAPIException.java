package main.java.co.omise.exeption;

import main.java.co.omise.model.OmiseError;

/**
 * OmiseAPIを叩いた際にパラメタが不正・不足している等の場合にに発生する
 * 詳細はhttps://docs.omise.co/api/errors/を参照
 */
@SuppressWarnings("serial")
public class OmiseAPIException extends OmiseException {
	public OmiseAPIException(String message, OmiseError omiseError) {
		super(message, omiseError);
	}
}
