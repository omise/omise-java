package co.omise.models;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;
import com.google.common.collect.ImmutableBiMap;

public class ModelTypeResolver extends TypeIdResolverBase {
    public static final ImmutableBiMap<String, Class> KNOWN_TYPES = new ImmutableBiMap.Builder<String, Class>()
            .put("account", Account.class)
            .put("balance", Balance.class)
            .put("bank_account", BankAccount.class)
            .put("card", Card.class)
            .put("charge", Charge.class)
            .put("customer", Customer.class)
            .put("dispute", Dispute.class)
            .put("event", Event.class)
            .put("recipient", Recipient.class)
            .put("token", Token.class)
            .put("transaction", Transaction.class)
            .put("transfer", Transfer.class)
            .build();

    @Override
    public JsonTypeInfo.Id getMechanism() {
        return JsonTypeInfo.Id.CUSTOM;
    }

    @Override
    public String idFromValue(Object value) {
        return idFromValueAndType(value, value.getClass());
    }

    @Override
    public String idFromValueAndType(Object value, Class<?> suggestedType) {
        return KNOWN_TYPES.inverse().get(suggestedType);
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) {
        Class klass = KNOWN_TYPES.get(id);
        if (klass == null) {
            return null;
        }

        JavaType[] typeArgs = klass.equals(Event.class) ?
                new JavaType[]{context.getTypeFactory().constructSimpleType(Model.class, new JavaType[]{})} :
                new JavaType[]{};

        return context.getTypeFactory().constructSimpleType(klass, typeArgs);
    }
}
