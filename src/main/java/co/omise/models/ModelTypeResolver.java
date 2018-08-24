package co.omise.models;

import co.omise.models.schedules.Occurrence;
import co.omise.models.schedules.Schedule;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.DatabindContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.jsontype.impl.TypeIdResolverBase;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

class ModelTypeResolver extends TypeIdResolverBase {
    private static Map<String, Class> types;

    static Map<String, Class> getKnownTypes() {
        if (types == null) {
            types = new HashMap<>();
            types.put("account", Account.class);
            types.put("balance", Balance.class);
            types.put("bank_account", BankAccount.class);
            types.put("card", Card.class);
            types.put("charge", Charge.class);
            types.put("customer", Customer.class);
            types.put("dispute", Dispute.class);
            types.put("event", Event.class);
            types.put("link", Link.class);
            types.put("refund", Refund.class);
            types.put("recipient", Recipient.class);
            types.put("schedule", Schedule.class);
            types.put("occurrence", Occurrence.class);
            types.put("token", Token.class);
            types.put("transaction", Transaction.class);
            types.put("transfer", Transfer.class);
            types.put("source", Source.class);
            types.put("receipt", Receipt.class);
            types.put("forex", Forex.class);
        }
        return Collections.unmodifiableMap(types);
    }

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
        return reverse(getKnownTypes()).get(suggestedType);
    }

    @Override
    public JavaType typeFromId(DatabindContext context, String id) {
        Class klass = getKnownTypes().get(id);
        if (klass == null) {
            return null;
        }

        JavaType[] typeArgs = klass.equals(Event.class) ?
                new JavaType[]{context.getTypeFactory().constructSimpleType(Model.class, new JavaType[]{})} :
                new JavaType[]{};

        return context.getTypeFactory().constructSimpleType(klass, typeArgs);
    }

    private Map<Class, String> reverse(Map<String, Class> map) {
        return map.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }
}