package main.java.co.omise.exeption;

import main.java.co.omise.model.OmiseError;

/**
 * main.java.co.omise.Omiseに公開鍵または秘密鍵を設定していない場合に発生する
 */
@SuppressWarnings("serial")
public class OmiseKeyUnsetException extends OmiseException {
	public OmiseKeyUnsetException(String message, OmiseError omiseError) {
		super(message, omiseError);
	}
	
	/**
	 * 
	 * @return {@code null}が帰る
	 */
	@Override
	public OmiseError getOmiseError() {
		return null;
	}
}
