package com.google.protobuf;

import java.util.List;

/* renamed from: com.google.protobuf.x0, reason: case insensitive filesystem */
/* loaded from: classes3.dex */
public interface InterfaceC2027x0 extends H2 {
    @Override // com.google.protobuf.H2, com.google.protobuf.O3
    /* synthetic */ N3 getDefaultInstanceForType();

    boolean getDeprecated();

    @Override // com.google.protobuf.H2
    /* synthetic */ Object getExtension(S1 s1);

    @Override // com.google.protobuf.H2
    /* synthetic */ Object getExtension(S1 s1, int i9);

    @Override // com.google.protobuf.H2
    /* synthetic */ int getExtensionCount(S1 s1);

    DescriptorProtos$UninterpretedOption getUninterpretedOption(int i9);

    int getUninterpretedOptionCount();

    List<DescriptorProtos$UninterpretedOption> getUninterpretedOptionList();

    boolean hasDeprecated();

    @Override // com.google.protobuf.H2
    /* synthetic */ boolean hasExtension(S1 s1);

    @Override // com.google.protobuf.H2, com.google.protobuf.O3
    /* synthetic */ boolean isInitialized();
}
