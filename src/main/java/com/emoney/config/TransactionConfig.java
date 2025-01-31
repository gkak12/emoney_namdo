package com.emoney.config;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.RollbackRuleAttribute;
import org.springframework.transaction.interceptor.RuleBasedTransactionAttribute;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

@Aspect
@Configuration
@RequiredArgsConstructor
public class TransactionConfig {

    private final int TX_METHOD_TIMEOUT = 10;
    private final String AOP_POINTCUT_EXPRESSION = "execution(* com.emoney..service..*ServiceImpl.*(..))";

    private final PlatformTransactionManager transactionManager;

    @Bean
    public TransactionInterceptor txAdvice() {
        TransactionInterceptor txAdvice = new TransactionInterceptor();
        Properties txAttributes = new Properties();

        List<RollbackRuleAttribute> rollbackRules = new ArrayList<>();
        rollbackRules.add(new RollbackRuleAttribute(Exception.class));

        DefaultTransactionAttribute readOnlyAttribute = new DefaultTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED);
        readOnlyAttribute.setReadOnly(true);
        readOnlyAttribute.setTimeout(TX_METHOD_TIMEOUT);

        RuleBasedTransactionAttribute writeAttribute = new RuleBasedTransactionAttribute(TransactionDefinition.PROPAGATION_REQUIRED, rollbackRules);
        writeAttribute.setTimeout(TX_METHOD_TIMEOUT);

        String readOnlyTransactionAttributesDefinition = readOnlyAttribute.toString();
        String writeTransactionAttributesDefinition = writeAttribute.toString();

        // read-only
        txAttributes.setProperty("find*", readOnlyTransactionAttributesDefinition);

        // write rollback-rule
        txAttributes.setProperty("create*", writeTransactionAttributesDefinition);
        txAttributes.setProperty("deduct*", writeTransactionAttributesDefinition);
        txAttributes.setProperty("useCancel*", writeTransactionAttributesDefinition);
        txAttributes.setProperty("approve*", writeTransactionAttributesDefinition);
        txAttributes.setProperty("reject*", writeTransactionAttributesDefinition);
        txAttributes.setProperty("extend*", writeTransactionAttributesDefinition);
        txAttributes.setProperty("expire*", writeTransactionAttributesDefinition);

        txAdvice.setTransactionAttributes(txAttributes);
        txAdvice.setTransactionManager(transactionManager);

        return txAdvice;
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(AOP_POINTCUT_EXPRESSION);

        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }
}
