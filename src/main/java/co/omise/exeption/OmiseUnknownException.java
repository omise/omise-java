package main.java.co.omise.exeption;

import main.java.co.omise.model.OmiseError;

/**
 * 定義されていない例外が発生した場合に返却される。
 * 主に、APIを叩いたが期待されるerrorオブジェクト以外が返却された場合等に発生する。
 * 詳細はStacktraceを参照してください。
 */
@SuppressWarnings("serial")
public class OmiseUnknownException extends OmiseException {
	public OmiseUnknownException(String message, OmiseError omiseError) {
		super(message, omiseError);
	}
}
