// Generated by view binder compiler. Do not edit!
package com.jamiltondamasceno.projetonetflixapi.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.jamiltondamasceno.projetonetflixapi.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityDetalhesBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final ImageView imgPoster;

  @NonNull
  public final TextView textFilmeTitulo;

  private ActivityDetalhesBinding(@NonNull ConstraintLayout rootView, @NonNull ImageView imgPoster,
      @NonNull TextView textFilmeTitulo) {
    this.rootView = rootView;
    this.imgPoster = imgPoster;
    this.textFilmeTitulo = textFilmeTitulo;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityDetalhesBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityDetalhesBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_detalhes, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityDetalhesBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.imgPoster;
      ImageView imgPoster = ViewBindings.findChildViewById(rootView, id);
      if (imgPoster == null) {
        break missingId;
      }

      id = R.id.textFilmeTitulo;
      TextView textFilmeTitulo = ViewBindings.findChildViewById(rootView, id);
      if (textFilmeTitulo == null) {
        break missingId;
      }

      return new ActivityDetalhesBinding((ConstraintLayout) rootView, imgPoster, textFilmeTitulo);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}