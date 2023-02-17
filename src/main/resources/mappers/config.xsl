<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:mybatis="http://mybatis.org/schema/mybatis-config"
                exclude-result-prefixes="mybatis">

    <xsl:output method="xml" indent="yes"/>

    <xsl:template match="/">
        <mybatis:configuration>
            <xsl:apply-templates/>
        </mybatis:configuration>
    </xsl:template>

    <xsl:template match="properties">
        <mybatis:properties>
            <xsl:apply-templates/>
        </mybatis:properties>
    </xsl:template>

    <xsl:template match="dataSource">
        <mybatis:dataSource type="{@type}">
            <xsl:apply-templates/>
        </mybatis:dataSource>
    </xsl:template>

    <xsl:template match="environment">
        <mybatis:environment id="{@id}">
            <xsl:apply-templates/>
        </mybatis:environment>
    </xsl:template>

    <xsl:template match="transactionManager">
        <mybatis:transactionManager type="{@type}"/>
    </xsl:template>

    <xsl:template match="mapper">
        <mybatis:mapper resource="{@resource}"/>
    </xsl:template>

</xsl:stylesheet>

